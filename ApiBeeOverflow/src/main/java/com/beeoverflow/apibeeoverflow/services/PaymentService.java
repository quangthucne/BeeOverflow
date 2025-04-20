package com.beeoverflow.apibeeoverflow.services;

import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.beeoverflow.apibeeoverflow.beans.ResultPaymentBean;
import com.beeoverflow.apibeeoverflow.utils.VnpayUtil;

@Service
public class PaymentService {
    public String getPayment (String orderId, int price) throws UnsupportedEncodingException, UnknownHostException {
        VnpayUtil payment = new VnpayUtil();
        return payment.getUrlPayment(orderId, price);
    }

    public ResultPaymentBean convertData (ResultPaymentBean returnResultPayment) {
        ResultPaymentBean resultPayment = new ResultPaymentBean();
        String status = getStatus(returnResultPayment);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime dateTime = LocalDateTime.parse(returnResultPayment.getVnp_PayDate(), formatter);
        System.out.println("Converted DateTime: " + dateTime);


        resultPayment.setDateTime(dateTime);
        resultPayment.setVnp_Amount(returnResultPayment.getVnp_Amount());
        resultPayment.setVnp_TxnRef(returnResultPayment.getVnp_TxnRef());
        resultPayment.setStatus(status);

        System.out.println("Datime: " + dateTime);
        System.out.println("Vnp_Amount: " + returnResultPayment.getVnp_Amount());
        System.out.println("Vnp_TxnRef: " + returnResultPayment.getVnp_TxnRef());
        System.out.println("Status: " + status);

        return resultPayment;
    }

    public String getStatus (ResultPaymentBean returnResultPayment) {
        String returnCode = returnResultPayment.getVnp_ResponseCode();
        System.out.println("Return Code: " + returnCode);
        String status = "";
        switch (returnCode) {
            case "00": return "Giao dịch thành công";
            case "07": return "Trừ tiền thành công. Giao dịch bị nghi ngờ (liên quan tới lừa đảo, giao dịch bất thường).";
            case "09": return "Giao dịch không thành công do: Thẻ/Tài khoản của khách hàng chưa đăng ký dịch vụ InternetBanking tại ngân hàng.";
            case "10": return "Giao dịch không thành công do: Khách hàng xác thực thông tin thẻ/tài khoản không đúng quá 3 lần.";
            case "11": return "Giao dịch không thành công do: Đã hết hạn chờ thanh toán. Xin quý khách vui lòng thực hiện lại giao dịch.";
            case "12": return "Giao dịch không thành công do: Thẻ/Tài khoản của khách hàng bị khóa.";
            case "13": return "Giao dịch không thành công do Quý khách nhập sai mật khẩu xác thực giao dịch (OTP). Xin quý khách vui lòng thực hiện lại giao dịch.";
            case "24": return "Giao dịch không thành công do: Khách hàng hủy giao dịch.";
            case "51": return "Giao dịch không thành công do: Tài khoản của quý khách không đủ số dư để thực hiện giao dịch.";
            case "65": return "Giao dịch không thành công do: Tài khoản của Quý khách đã vượt quá hạn mức giao dịch trong ngày.";
            case "75": return "Ngân hàng thanh toán đang bảo trì.";
            case "79": return "Giao dịch không thành công do: KH nhập sai mật khẩu thanh toán quá số lần quy định. Xin quý khách vui lòng thực hiện lại giao dịch.";
            case "99": return "Các lỗi khác (lỗi còn lại, không có trong danh sách mã lỗi đã liệt kê).";
            default: return "Lỗi không xác định. Vui lòng thử lại sau!";
        }
    }
}
