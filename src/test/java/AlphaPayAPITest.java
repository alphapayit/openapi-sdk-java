import com.alibaba.fastjson.JSON;
import com.alphapay.api.AlphaPayClient;
import com.alphapay.api.DefaultAlphaPayClient;
import com.alphapay.api.exception.AlphaPayApiException;
import com.alphapay.api.model.beans.*;
import com.alphapay.api.request.beans.*;
import com.alphapay.api.response.beans.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.alphapay.api.APIs.*;

/**
 * AlphaPay API Test
 * reference: https://apidoc.alphapay.com/open-api/
 */
public class AlphaPayAPITest {

    private static final String GATE_WAY_URL = "https://openapi.alphapay.ca";

    /**
     * Please use [RSA Key Generator.html] to generate RSA key pair
     */
    private static String merchantPrivateKey = "-----BEGIN PRIVATE KEY-----\n" + "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDNMQTaKFENc3sK\n" + "QbmXo633lV64LMKp7x0757hfGIbvL8++sCl3zb3TiWjeH9x+CdSAb5jRWiZr1pCf\n" + "PU253p1pPn83vvbCVdppzLKWVIqHYU12z5lq1rTVwDq9OQlPiI42prG67HoHK5eT\n" + "DC6QHPKuuDtWfXWsXego8qDA9AyQbaCZpScs+rMLyrD9b9ETOg8fYNcKrNcNPm9A\n" + "zN/csFNbrE2g/N6LAm66FQf2c6d5gyxAWKXovXkOiZT9ui6u8PV/XxabI4MgqI8U\n" + "sOv8D+6WfnWjQwkYYKnY0KFbmLn23BnlkUk4RK9fWoyGn4GVXYSpHv6jp/19MSTu\n" + "zlu222JnAgMBAAECggEAVPhenQSjA+H1arrq4wup5+PYFIacmghl37/GTsL2Zagz\n" + "7DBBC9aMLEQzxAAHq73gXMtZyh/91xxESVz2iejQ4Js7zVbsEm/BAZz9u1kJAKTf\n" + "kjrHukFtIzL2YBN1cpUG50TCRrjUk5GOtBsQUSdbq9So5dkQWK+Oix5mSWEGruuo\n" + "+yEyW5p/uNVHS5PE/9FbJVpOYtdp72vEUT8IT3c8LJRhfQkaQISPIP0E1znW90Yd\n" + "mtBKFL6X4T0lorjXzljQhMzS1VyQaRuum6FwmeBxpPndekNfAt55SXybKfhFTZfX\n" + "7w94daVgdBRVzQUiAOc9+FwCMKjTjemKSisgd3njgQKBgQDrE4VUB2zGAOXHZAzO\n" + "rfy08+3px1mtEmlwwYyEms4EhORMzs5QjsVgMHo238z6iXJFQrOvA4MCCk/jC5p2\n" + "/S1hwf/Ex+6e7AmYKBwGwwMUGchuOFCz8bicx8SjJP0WhbXXQBgQ2O4V54JtPNtW\n" + "u1Z7OXWgSrkA5Fp6HHSUdll/wQKBgQDfdIooUYNeEI3OxfdTv7UafacOOso6eFlo\n" + "D42Kcg/t0FCBn6TltwzBD045Glx5cT/EJXFgdXrKoakZXDFLqEmXhDviNBtz92Gu\n" + "efPMPdKlvmWn/j46MH/1rF7MaVfx+1S2Zg1b+we4cQkgmvv9vjxcwstLJ4+9kZfZ\n" + "/5sdOjPsJwKBgBf3w45Pu31stgDgaJXiE3P3svg1BSWqTOncMhGLHxpC2mwiYdLA\n" + "28r05mEV7HZW8TmylVNwzji1CHQjyLn9ElV3TDaqE+BMPKYw4Lqnw3aXMmJah4/x\n" + "Q1to2tmvecaEF4g81rdRBuVg12F/5PUw4J5ru1x+geJfZgIxSImij3mBAoGAabG/\n" + "E7kSsTCVUuPSy/tW13Pty85FD4kTiSQqJ/0xyaQKIHX50mpWDw+FSX9NlGfb6JRp\n" + "X13SOfxjdspjysyMGq4CgUlyXtT5kETA66+mbp1zihW5K177d6ITqQ5++ceO10PI\n" + "N+SyTyywS4rjfK858Ap3jFHnLJysPgIoRZfdLWsCgYA+HpW7B9kNBrHrDKRxGm8e\n" + "s0vOq2hFXOUVgO4M+AD+QPyO7eRVniIizPiQRO3SiwAkcc27V221ghV9BuGyxr5m\n" + "oGn2eS/XeDk479AhtWGQlahJEDOD7PeZXiFCu+J6j+guiafSmXuusgST+jPBusbv\n" + "39f5diHm6c+n6w2I42fceg==\n" + "-----END PRIVATE KEY-----";

    private static final String alphaPayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkEO8REXYwfsfGW4lWG2c11s+ERO4KY/31o2hZS+e4Lj9DFE1KmhCVawi/Sp2wUsnPYr3PaRM4f2Di1oQV9OQ9vAwLqKkG/JhuJ4Jw5iimZiDSrpjNh9RAxjHRCpmcfImDiZ4vjro9oW7o4Dvp+smiDuv09OWs2pjasMAh+sDB30ASZDWOIutSjGPkKdOrYEinAkKUtQAQTqoZbHbnwwCCRI5RJfzFJHZdmbT4QZXemfXZqSncPNFl4uoKPDLG9niaz2LiexoPFCx2KHMzwhPHkfq3oKFQfFdK1NHFV+/bfzksC3DODIX3+GlsFkJo1vWBDlTeqIkHCTA2SQzyeyFOQIDAQAB";

    private static final String MERCHANT_CODE = "9FEOWJ";

    private static AlphaPayClient defaultAlphaPayClient;


    @BeforeAll
    public static void testBefore() {
        merchantPrivateKey = merchantPrivateKey.replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "").replaceAll("\\s+", "");

        defaultAlphaPayClient = new DefaultAlphaPayClient(GATE_WAY_URL, merchantPrivateKey, alphaPayPublicKey);

    }

    /**
     * Test order
     */
    @Test
    public void testOrder() {
        String paymentRequestId = UUID.randomUUID().toString();
        String scenarioCode = "ONLINE_WEB";
        commonOrder(paymentRequestId, scenarioCode);
    }

    @Test
    public void testOrderOffLine() {
        String paymentRequestId = UUID.randomUUID().toString();
        String scenarioCode = "OFFLINE_QRCODE";
        commonOrder(paymentRequestId, scenarioCode);
    }


    private static void commonOrder(String paymentRequestId, String scenarioCode) {

        System.out.println("Request orderId:" + paymentRequestId);

        CreateOrderRequest request = new CreateOrderRequest();
        request.setMerchantCode(MERCHANT_CODE);
        request.setPath(PAY);

        // 测试 ONLINE_WEB是能支付成功的
        request.setScenarioCode(scenarioCode);

        request.setPaymentRequestId(paymentRequestId);

        // online pay must set payment method, but offline pay can not set payment method
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setPaymentCode("ALIPAY");
        paymentMethod.setPaymentMethodType("Alipay");


        request.setPaymentMethod(paymentMethod);
        Order order = new Order();
        Amount amount = new Amount();
        amount.setValue("1");
        amount.setCurrency("CAD");
        order.setOrderAmount(amount);
        order.setDescription("Test OFFLINE_QRCODE");
        order.setNotifyUrl("https://alphapay.com/success");
        order.setRedirectUrl("https://alphapay.com/successPage");
        request.setOrder(order);
        try {
            CreateOrderResponse response = defaultAlphaPayClient.execute(request);
            Assertions.assertNotNull(response);
            System.out.println("response:" + JSON.toJSONString(response));


            // You can use paymentUrl or paymentQRCode to redirect user to payment page
            PaymentInfo paymentInfo = response.getPaymentInfo();
            String paymentUrl = paymentInfo.getPaymentUrl();

            System.out.println(paymentInfo.getPaymentQRImage());
            System.out.println(paymentUrl);

            System.out.println("paymentId:" + response.getPaymentId());

            Assertions.assertEquals(response.getResult().getResultCode(), "PAYMENT_IN_PROCESS");
        } catch (AlphaPayApiException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Test query order
     */
    @Test
    public void testQueryOrder() {
        // User paymentRequestId to query order
        String paymentId = "2506031505244756658153857";
        System.out.println("Request orderId:" + paymentId);

        SearchOrderRequest request = new SearchOrderRequest();
        request.setMerchantCode(MERCHANT_CODE);
        request.setPath(SEARCH_ORDER);

        request.setPaymentId(paymentId);

        try {
            SearchOrderResponse response = defaultAlphaPayClient.execute(request);
            Assertions.assertNotNull(response);
            String jsonString = JSON.toJSONString(response);
            System.out.println("search response:" + jsonString);
            Assertions.assertEquals(response.getResult().getResultCode(), "SUCCESS");
        } catch (AlphaPayApiException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Test refund
     */
    @Test
    public void testRefund() {
        // todo you need replace it
        String paymentId = "2506031505244756658153857";
        Refund refund = new Refund();
        Amount refundAmount = new Amount();
        refundAmount.setValue("1");
        refundAmount.setCurrency("CAD");
        refund.setRefundAmount(refundAmount);
        refund.setDescription("Test refund");

        CreateRefundRequest request = new CreateRefundRequest();
        request.setMerchantCode(MERCHANT_CODE);
        request.setPath(REFUND);
        request.setPaymentId(paymentId);
        request.setRefund(refund);

        try {
            CreateRefundResponse response = defaultAlphaPayClient.execute(request);
            Assertions.assertNotNull(response);
            System.out.println("refund response:" + JSON.toJSONString(response));
            Assertions.assertEquals(response.getResult().getResultCode(), "SUCCESS");
        } catch (AlphaPayApiException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Test query refund
     */
    @Test
    public void testQueryRefund() {
        // Use previous "testRefund" method response refundId to query refund
        String refundId = "7b46758a-464c-42e8-b8ff-cbe779c2299f";
        String paymentRequestId = "a19fd1fb-c341-41cd-bd16-b503b7e77a68";

        SearchRefundRequest request = new SearchRefundRequest();
        request.setMerchantCode(MERCHANT_CODE);
        request.setPath(SEARCH_REFUND);
        request.setRefundId(refundId);
        request.setRefundRequestId(paymentRequestId);

        try {
            SearchRefundResponse response = defaultAlphaPayClient.execute(request);
            Assertions.assertNotNull(response);
            System.out.println("search refund response:" + JSON.toJSONString(response));
            Assertions.assertEquals(response.getResult().getResultCode(), "SUCCESS");
        } catch (AlphaPayApiException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Test cancel order for ORDER_PAID
     */
    @Test
    public void testCancelOrder_ORDER_PAID() {
        // todo you need replace it
        String paymentId = "2506031505244756658153857";
        CancelOrderRequest request = new CancelOrderRequest();
        request.setMerchantCode(MERCHANT_CODE);
        request.setPath(CANCEL);
        request.setPaymentId(paymentId);

        try {
            CancelOrderResponse response = defaultAlphaPayClient.execute(request);
            Assertions.assertNotNull(response);
            System.out.println("search refund response:" + JSON.toJSONString(response));
            Assertions.assertEquals(response.getResult().getResultCode(), "ORDER_PAID");
        } catch (AlphaPayApiException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Test cancel order
     * require: Ordered by user, but no paid
     */
    @Test
    public void testCancelOrder_Success() {

        // offline paymentId
        String paymentId = "2506031554146922458157377";
        CancelOrderRequest request = new CancelOrderRequest();
        request.setMerchantCode(MERCHANT_CODE);
        request.setPath(CANCEL);
        request.setPaymentId(paymentId);

        try {
            CancelOrderResponse response = defaultAlphaPayClient.execute(request);
            Assertions.assertNotNull(response);
            System.out.println("search refund response:" + JSON.toJSONString(response));
            Assertions.assertEquals(response.getResult().getResultCode(), "SUCCESS");
        } catch (AlphaPayApiException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Query transaction
     */
    @Test
    public void testQueryTransaction() {

        SearchTransactionsRequest request = new SearchTransactionsRequest();
        request.setMerchantCode(MERCHANT_CODE);
        request.setPath(SEARCH_TRANSACTIONS);
        request.setDate("20250603");
        request.setPage(1);
        request.setPageLimit(10);
        request.setTransactionType(TransactionType.All);


        try {
            SearchTransactionsResponse response = defaultAlphaPayClient.execute(request);
            Assertions.assertNotNull(response);
            System.out.println("search refund response:" + JSON.toJSONString(response));
            Assertions.assertEquals(response.getResult().getResultCode(), "SUCCESS");
            response.getTransactions().forEach(transaction -> {
                System.out.println("transaction:" + JSON.toJSONString(transaction));
            });
        } catch (AlphaPayApiException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Query transaction
     */
    @Test
    public void testQuerySettlements() {

        SearchSettlementsRequest request = new SearchSettlementsRequest();
        request.setMerchantCode(MERCHANT_CODE);
        request.setPath(SEARCH_SETTLEMENTS);
        request.setDate("20250603");


        try {
            SearchSettlementsResponse response = defaultAlphaPayClient.execute(request);
            Assertions.assertNotNull(response);
            System.out.println("search refund response:" + JSON.toJSONString(response));
            Assertions.assertEquals(response.getResult().getResultCode(), "SUCCESS");
            response.getSettlements().forEach(transaction -> {
                System.out.println("settlement:" + JSON.toJSONString(transaction));
            });
        } catch (AlphaPayApiException e) {
            throw new RuntimeException(e);
        }
    }


}
