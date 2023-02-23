package fr.iut;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "qrcode", urlPatterns = {"/qrcode"})
public class QRCodeServlet extends HttpServlet {
    @Override
    protected void doGet(final HttpServletRequest request,
                         final HttpServletResponse response)
            throws ServletException, IOException {
        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix bitMatrix = null;
        try {
            String url = "coucou";
            bitMatrix = writer.encode(url, BarcodeFormat.QR_CODE, 300, 300);
        } catch (WriterException e) { e.printStackTrace(); }
        response.setContentType("image/png");
        MatrixToImageWriter.writeToStream(bitMatrix, "png", response.getOutputStream());
    }
}