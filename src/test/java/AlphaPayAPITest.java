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
    private static String merchantPrivateKey = "-----BEGIN PRIVATE KEY----- YOUR private KEY  -----END PRIVATE KEY-----";

    private static final String alphaPayPublicKey = "PUBLIC KEY ";

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
