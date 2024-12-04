package test;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import model.SimpleJsonFile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class ParsingTest {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ClassLoader classLoader = ParsingTest.class.getClassLoader();

    private InputStream extractFileFromZip(String fileNameToExtract, String zipFilePath) throws Exception {
        ZipInputStream zis = new ZipInputStream(classLoader.getResourceAsStream(zipFilePath));
        ZipEntry zipEntry;
        while ((zipEntry = zis.getNextEntry()) != null) {
            if (zipEntry.getName().equals(fileNameToExtract)) {
                return zis;
            }
        }
        return InputStream.nullInputStream();
    }

    @Test
    void pdfParsingTest() throws Exception {
        try (InputStream pdfInputStream = extractFileFromZip("examplePdf.pdf", "SimpleZip.zip")) {
            PDF pdf = new PDF(pdfInputStream);
            assertThat(pdf.text).contains("Пример pdf");
        }
    }

    @Test
    void xlsParsingTest() throws Exception {
        try (InputStream xlsInputStream = extractFileFromZip("exampleXls.xlsx", "SimpleZip.zip")) {
            XLS xls = new XLS(xlsInputStream);
            assertThat(xls.excel.getSheet("1").getRow(0).getCell(1).toString())
                    .contains("not-anounced");
            assertThat(xls.excel.getSheet("1").getRow(0).getCell(2).toString())
                    .contains("true");
        }
    }


    @Test
    void csvParsingTest() throws Exception {
        try (InputStream is = extractFileFromZip("exampleCsv.csv", "SimpleZip.zip");
             CSVReader csvReader = new CSVReader(new InputStreamReader(is))) {
            //csvReader.skip(0);

            List<String[]> data = csvReader.readAll();

            Assertions.assertEquals(7, data.size());
            Assertions.assertArrayEquals(
                    new String[]{"20023374", "not-anounced"},
                    data.get(0)
            );
            Assertions.assertArrayEquals(
                    new String[]{"20028333", "announce"},
                    data.get(1)
            );
        }
    }


    @Test
    void jsonFileParsingTest() throws Exception {

        try (Reader reader = new InputStreamReader(classLoader.getResourceAsStream("simpleJsonFile.json"))) {

            SimpleJsonFile actual = objectMapper.readValue(reader, SimpleJsonFile.class);

            Assertions.assertEquals("30020955", actual.getMaterialNumber());
            Assertions.assertEquals("Цифровой помощник", actual.getMaterialName());

            Assertions.assertEquals("1", actual.getServiceMaterialListInner().getId());
            Assertions.assertEquals("6016503", actual.getServiceMaterialListInner().getMaterialServiceNumber());
            Assertions.assertEquals("materialServiceName", actual.getServiceMaterialListInner().getMaterialServiceName());
            Assertions.assertEquals("price", actual.getServiceMaterialListInner().getPrice());


        }
    }
}

