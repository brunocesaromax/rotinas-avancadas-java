package services;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class RelatorioService implements Serializable {

    private static final String FOLDER_RELATORIOS = "/relatorios";
    private static final String SUBREPORT_DIR = "SUBREPORT_DIR"; //Relatório acoplado dentro de um relatório maior
    private String SEPARATOR = File.separator; // '\', '/' , depende do SO
    private String caminhoArquivoRelatorio = null;
    private JRExporter exporter = null;
    private String caminhoSubReport_Dir = "";
    private File arquivoGerado = null;

    public String gerarRelatorio(List<?> listaDataBeanCollection, HashMap parametrosRelatorio
            , String nomeRelatorioJasper, String nomeRelatorioSaida, ServletContext servletContext) throws Exception {

        /*Cria  a lista de collectionDataSources de beans que carregam os dados para o relatório*/
        JRBeanCollectionDataSource collectionDataSource = new JRBeanCollectionDataSource(listaDataBeanCollection);

        /*Fornece o caminho físico até a pasta que contém os relatórios .jasper*/
        String caminhoRelatorio = servletContext.getRealPath(FOLDER_RELATORIOS);

        File file = new File(caminhoRelatorio + SEPARATOR + nomeRelatorioJasper + ".jasper");

        if (caminhoRelatorio == null || caminhoRelatorio.isEmpty() || !file.exists()) {
            caminhoRelatorio = this.getClass().getResource(FOLDER_RELATORIOS).getPath();
            SEPARATOR = "";
        }

        /*caminho para imagens*/
        parametrosRelatorio.put("REPORT_PARAMETERS_IMG", caminhoRelatorio);

        /*caminho completo até o relatório compilado indicado*/
        String caminhoArquivosJasper = caminhoRelatorio + SEPARATOR + nomeRelatorioJasper + ".jasper";

        /*Faz o carregamento do relatório*/
        JasperReport relatorioJasper = (JasperReport) JRLoader.loadObjectFromFile(caminhoArquivosJasper);

        /*sera parametros SUBREPORT_DIR com o caminho físico para subreport*/
        caminhoSubReport_Dir = caminhoRelatorio + SEPARATOR;
        parametrosRelatorio.put(SUBREPORT_DIR, caminhoSubReport_Dir);

        /*Carrega o arquivo*/
        JasperPrint impressoraJasper = JasperFillManager.fillReport(relatorioJasper, parametrosRelatorio, collectionDataSource);

        exporter = new JRPdfExporter();

        /*Caminho relatorio exportado*/
        caminhoArquivoRelatorio = caminhoRelatorio + SEPARATOR + nomeRelatorioSaida + ".pdf";

        /*Criar novos arquivos exportados*/
        arquivoGerado = new File(caminhoArquivoRelatorio);

        /*Prepara a impressão*/
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, impressoraJasper);

        exporter.setParameter(JRExporterParameter.OUTPUT_FILE, arquivoGerado);

        /*Executa a exportação*/
        exporter.exportReport();

        /*Remove o arquivo do servidor após ser feito o download*/
        arquivoGerado.deleteOnExit();

        return caminhoArquivoRelatorio;
    }

}
