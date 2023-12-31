package com.sc.accounting_smart_cookies.service.implementation;

import com.sc.accounting_smart_cookies.mapper.MapperUtil;
import com.sc.accounting_smart_cookies.repository.InvoiceRepository;
import com.sc.accounting_smart_cookies.dto.InvoiceProductDTO;
import com.sc.accounting_smart_cookies.entity.InvoiceProduct;
import com.sc.accounting_smart_cookies.enums.InvoiceStatus;
import com.sc.accounting_smart_cookies.enums.InvoiceType;
import com.sc.accounting_smart_cookies.repository.InvoiceProductRepository;
import com.sc.accounting_smart_cookies.service.InvoiceProductService;
import com.sc.accounting_smart_cookies.service.ReportingService;
import com.sc.accounting_smart_cookies.service.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;


@Service
@AllArgsConstructor
public class ReportingServiceImpl implements ReportingService {
        private final SecurityService securityService;
        private final InvoiceProductService invoiceProductService;

    @Override
    public Map<String, BigDecimal> listMonthlyProfitLoss() {

        List<InvoiceProductDTO> salesList = invoiceProductService.getAllProductWithStatusTypeAndCompanyTitle(
                InvoiceStatus.APPROVED, InvoiceType.SALES, securityService.getLoggedInUser().getCompany().getTitle());

        Map<String, BigDecimal> monthlyProfitLoss = new HashMap<>();

        salesList.forEach(invoiceProduct -> {
            String year = String.valueOf(invoiceProduct.getInvoice().getDate().getYear());
            String month = invoiceProduct.getInvoice().getDate().getMonth().toString();
            BigDecimal profitLoss = invoiceProduct.getProfitLoss();
            BigDecimal totalProfitloss = monthlyProfitLoss.getOrDefault(year + " " + month, BigDecimal.ZERO).add(profitLoss);
            monthlyProfitLoss.put(year + " " + month, totalProfitloss);
        });

        return monthlyProfitLoss;
    }

}
