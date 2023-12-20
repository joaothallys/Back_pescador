package edu.br.puc.goias.clube.Service;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import edu.br.puc.goias.clube.Controller.DTOS.MembroDTO;
import edu.br.puc.goias.clube.Model.Membro;
import edu.br.puc.goias.clube.Repository.Autowired;
import edu.br.puc.goias.clube.Repository.MembroRepository;


@Service
public class VisualizarRelatorioService {
    @Autowired
    private MembroRepository iCliente;

    public JasperPrint preencherRelatorioMembrosSemParametros() throws JRException {
        try {
            // Lógica para obter a lista de membros DTO
            List<MembroDTO> listaMembrosDTO = obterListaMembrosDTO();

            // Carregar o arquivo JRXML usando "classpath:"
            File file = ResourceUtils.getFile("classpath:edu/br/puc/goias/clube/Membro.jrxml");

            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

            // Criar uma fonte de dados a partir da lista de membros DTO
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listaMembrosDTO);

            // Preencher o relatório
            return JasperFillManager.fillReport(jasperReport, null, dataSource);
        } catch (IOException e) {
            throw new JRException("Erro ao carregar o arquivo JRXML", e);
        }
    }

    // Método fictício para obter a lista de membros DTO (implemente conforme necessário)
    private List<MembroDTO> obterListaMembrosDTO() {
        // Implemente a lógica para obter a lista de membros DTO, pode ser do banco de dados ou outra fonte de dados
        // Retorna uma lista de MembroDTO
        // Exemplo fictício:
        // return membroDTOService.obterTodosMembrosDTO();
        return Collections.emptyList(); // Retorna uma lista vazia se não houver membros
    }

    public JasperPrint exportaRelatorio() throws IOException, JRException {
        // Recuperar todos os produtos do banco de dados
        List<Membro> produtos = iCliente.findAll();

        // Converter entidade Produto para DTO (caso tenha DTO)
        List<MembroDTO> listClienteDTO = produtos.stream()
                .map(MembroDTO::new)
                .collect(Collectors.toList());

        // Mudar o classPath de acordo com o arquivo
        File file = new File("C:/Users/POLICHAT/JaspersoftWorkspaceV2/MyReports/Membro.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listClienteDTO);

        // Montar o relatório
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);

        return jasperPrint;
    }
}
