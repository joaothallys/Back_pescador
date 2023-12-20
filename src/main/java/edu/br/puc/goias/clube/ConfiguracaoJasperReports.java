package edu.br.puc.goias.clube;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Configuration
public class ConfiguracaoJasperReports {

    @Bean
    public JasperReport jasperReport() throws JRException, IOException {
        String jrxmlPath = "/edu/br/puc/goias/clube/Membro.jrxml";
        ClassPathResource resource = new ClassPathResource(jrxmlPath);
        try (InputStream inputStream = resource.getInputStream()) {
            return JasperCompileManager.compileReport(inputStream);
        }
    }

    @Bean
    public JasperPrint jasperPrint(JasperReport jasperReport) throws JRException {
        return JasperFillManager.fillReport(jasperReport, new HashMap<>(), new JREmptyDataSource());
    }
}

