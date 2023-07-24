//package filter;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.crypto.Cipher;
//import javax.crypto.SecretKey;
//import javax.crypto.spec.SecretKeySpec;
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.Base64;
//
//@Slf4j
//public class errorFilter implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        // 初始化，如果需要的話
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest httpRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        HttpSession session = httpRequest.getSession();
//        HttpServletResponse res = (HttpServletResponse) response;
//        try {
//            log.info("2222222333");
//            // 獲取當前的HttpServletRequest
////            HttpServletRequest httpRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//
//            // 獲取Session資料
//            byte[] sessionData = (byte[]) httpRequest.getSession().getAttribute("encryptedData");
//
//            System.out.println("Before method execution...");
//
//            byte[] keyBytes = Base64.getDecoder().decode("2c6XDV2uXsi8lwT0RgRbUtnOlw1drl7I");
//            SecretKey customSecretKey = new SecretKeySpec(keyBytes, "AES");
//            // 解密操作
//            Cipher decryptCipher = Cipher.getInstance("AES");
//            decryptCipher.init(Cipher.DECRYPT_MODE, customSecretKey);
//            byte[] decryptedData = decryptCipher.doFinal(sessionData);
//
//            // 输出解密后的结果
//            System.out.println("解密後結果=>" + new String(decryptedData));
//
////        String errorMessage = (String) request.getAttribute("errorMessage");
////        request.setAttribute("errorMessage", errorMessage);
//            String errorInfo = "發生錯誤：Something went wrong!";
//
//            session.setAttribute("errorInfo", errorInfo);
//
//
////            if (errorInfo != null) {
////                log.info("11111111111111");
////                // 執行頁面跳轉到登入頁面或其他頁面，並在頁面上顯示錯誤訊息
//////                request.getRequestDispatcher("emp-web/index").forward(request, response);
////                res.sendRedirect(req.getContextPath());
////            }
//            // 執行其他Filter或Servlet，讓請求繼續處理
//            chain.doFilter(request, response);
//        } catch (Exception ex) {
//            log.info("11111111111111");
////                 執行頁面跳轉到登入頁面或其他頁面，並在頁面上顯示錯誤訊息
////            request.getRequestDispatcher("emp-web/index").forward(request, response);
////                session.setAttribute("message", "登入失敗");
////                res.sendRedirect("http://localhost:8081/home/login");//http://localhost:8080/SpringTest/
//            res.sendRedirect(httpRequest.getContextPath());
//            log.error("", ex);
//        }
//    }
//
//    @Override
//    public void destroy() {
//        // 銷毀時的處理，如果需要的話
//    }
//}
