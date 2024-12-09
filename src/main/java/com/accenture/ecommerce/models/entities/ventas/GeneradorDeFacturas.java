package com.accenture.ecommerce.models.entities.ventas;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.io.IOException;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class GeneradorDeFacturas {
    public void generarFactura(Venta venta, String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String jsonPayload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(venta);
        JsonNode rootNode = objectMapper.readTree(jsonPayload);

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.beginText();
                contentStream.setLeading(14.5f);
                contentStream.newLineAtOffset(50, 750);

                contentStream.showText("Factura");
                contentStream.newLine();
                contentStream.newLine();
                contentStream.showText("Fecha: " + rootNode.get("fechaGeneracion").asText());
                contentStream.newLine();
                contentStream.newLine();
                contentStream.showText("Vendedor " + rootNode.get("vendedor").asText());
                contentStream.newLine();
                contentStream.showText("Cliente " + rootNode.get("cliente").asText());
                contentStream.newLine();
                contentStream.showText("Detalle de productos:");
                contentStream.newLine();

                JsonNode items = rootNode.get("productos");
                for (JsonNode item : items) {
                    String itemText = String.format("- %s x%d: $%.2f",
                            item.get("producto").get("nombre").asText(),
                            item.get("cantidad").asInt(),
                            item.get("precioUnitario").asDouble());
                    contentStream.showText(itemText);
                    contentStream.newLine();
                }

            contentStream.endText();
        } catch (Exception e) {
                throw new RuntimeException(e);
            }

        document.save(path);
        System.out.println("PDF generado exitosamente: " + path);
    }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
