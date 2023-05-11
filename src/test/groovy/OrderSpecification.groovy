import com.alibaba.fastjson.JSONObject
import com.alphapay.api.APIs
import com.alphapay.api.AlphaPayClient
import com.alphapay.api.DefaultAlphaPayClient
import com.alphapay.api.model.ResultStatusType
import com.alphapay.api.model.beans.*
import com.alphapay.api.request.beans.CreateOrderRequest
import com.alphapay.api.response.beans.CreateOrderResponse
import org.apache.commons.lang3.RandomStringUtils
import org.apache.commons.lang3.time.DateFormatUtils
import spock.lang.Specification

class OrderSpecification extends Specification {

    def "create order except credit card"() {
        given:
        def merchantCode = "PFA9QX"
        def gateway = System.getenv("OPEN_API_GATEWAY")
        def merchantKey = System.getenv("TEST_MCH_KEY")
        def platformKey = System.getenv("TEST_PLT_KEY")
        AlphaPayClient client = new DefaultAlphaPayClient(gateway, merchantKey, platformKey)

        when:
        def mchOrderId = 'PTM000' + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS") + "-" + RandomStringUtils.random(3, true, false).toUpperCase()
        CreateOrderRequest request = new CreateOrderRequest()
        request.setPath(APIs.PAY)
        request.setMerchantCode(merchantCode)
        request.setScenarioCode(scenarioCode)
        request.setPaymentRequestId(mchOrderId)
        Order order = new Order()
        order.setDescription("小浣熊干脆面 x3")
        Amount orderAmount = new Amount()
        orderAmount.setValue(amount)
        orderAmount.setCurrency(currency)
        order.setOrderAmount(orderAmount)
        request.setOrder(order)

        if (paymentCode != null || channel != null) {
            PaymentMethod paymentMethod = new PaymentMethod()
            paymentMethod.setPaymentCode(paymentCode)
            paymentMethod.setPaymentMethodType(channel)
            request.setPaymentMethod(paymentMethod)
        }
        if (appId != null || customerId != null) {
            Customer customer = new Customer()
            customer.setAppId(appId)
            customer.setCustomerId(customerId)
            request.setCustomer(customer)
        }
        if (osType != null || version != null) {
            Env env = new Env()
            env.setOsType(osType)
            env.setVersion(version)
            request.setEnv(env)
        }
        CreateOrderResponse response = client.execute(request)

        then:
        System.out.println(JSONObject.toJSONString(response))
        response.result != null
        response.result.resultCode == resultCode
        response.result.resultStatus != ResultStatusType.F
        response.scenarioCode == scenarioCode
        response.paymentId != null
        response.paymentRequestId != null
        response.paymentCreateTime != null
        response.paymentExpireTime != null
        response.order != null
        if (hasPaymentInfo) {
            response.paymentInfo != null
            response.paymentInfo.paymentUrl != null
            response.paymentInfo.paymentQRImage != null
        }

        if (hasPaymentCode) {
            response.paymentMethod != null
            response.paymentMethod.paymentCode != null
        }

        if (hasPaymentMethodType) {
            response.paymentMethod != null
            response.paymentMethod.paymentMethodType != null
        }

        if (hasAppId) {
            response.customer != null
            response.customer.appId != null
        }
        if (hasCustomerId) {
            response.customer != null
            response.customer.customerId != null
        }

        if (hasSdkParams) {
            response.paymentInfo != null
            response.paymentInfo.sdkParams != null
        }

        if (hasPayUrl) {
            response.paymentInfo != null
            response.paymentInfo.payUrl != null
        }

        where:
        scenarioCode     | amount | currency | paymentCode          | channel  | appId | customerId | osType | version || resultCode           | hasPaymentInfo | hasPaymentCode | hasPaymentMethodType | hasAppId | hasCustomerId | hasSdkParams | hasPayUrl
        "OFFLINE_QRCODE" | "100"  | "CAD"    | null                 | null     | null  | null       | null   | null    || "PAYMENT_IN_PROCESS" | true           | false          | false                | false    | false         | false        | false
        "OFFLINE_QRCODE" | "100"  | "CNY"    | null                 | null     | null  | null       | null   | null    || "PAYMENT_IN_PROCESS" | true           | false          | false                | false    | false         | false        | false
        "OFFLINE"        | "100"  | "CAD"    | "133415595913801642" | null     | null  | null       | null   | null    || "PAYMENT_IN_PROCESS" | false          | true           | false                | false    | false         | false        | false
        "OFFLINE"        | "100"  | "CNY"    | "133415595913801642" | null     | null  | null       | null   | null    || "PAYMENT_IN_PROCESS" | false          | true           | false                | false    | false         | false        | false
        "JSAPI"          | "100"  | "CAD"    | null                 | "Wechat" | null  | null       | null   | null    || "PAYMENT_IN_PROCESS" | false          | false          | true                 | false    | false         | false        | true
        "JSAPI"          | "100"  | "CNY"    | null                 | "Wechat" | null  | null       | null   | null    || "PAYMENT_IN_PROCESS" | false          | false          | true                 | false    | false         | false        | true
        "ONLINE_WEB"     | "100"  | "CAD"    | null                 | "Alipay" | null  | null       | null   | null    || "PAYMENT_IN_PROCESS" | false          | false          | true                 | false    | false         | false        | true
        "ONLINE_WEB"     | "100"  | "CNY"    | null                 | "Alipay" | null  | null       | null   | null    || "PAYMENT_IN_PROCESS" | false          | false          | true                 | false    | false         | false        | true
        "ONLINE_QRCODE"  | "100"  | "CAD"    | null                 | "Wechat" | null  | null       | null   | null    || "PAYMENT_IN_PROCESS" | true           | false          | true                 | false    | false         | false        | true
        "ONLINE_QRCODE"  | "100"  | "CNY"    | null                 | "Wechat" | null  | null       | null   | null    || "PAYMENT_IN_PROCESS" | true           | false          | true                 | false    | false         | false        | true
    }
}
