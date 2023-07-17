package com.sc.accounting_smart_cookies.service.implementation;


import com.sc.accounting_smart_cookies.TestDocumentInitializer;
import com.sc.accounting_smart_cookies.dto.InvoiceDTO;
import com.sc.accounting_smart_cookies.dto.InvoiceProductDTO;
import com.sc.accounting_smart_cookies.dto.ProductDTO;
import com.sc.accounting_smart_cookies.entity.*;
import com.sc.accounting_smart_cookies.enums.InvoiceStatus;
import com.sc.accounting_smart_cookies.mapper.MapperUtil;
import com.sc.accounting_smart_cookies.repository.InvoiceProductRepository;
import com.sc.accounting_smart_cookies.service.InvoiceProductService;
import com.sc.accounting_smart_cookies.service.InvoiceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InvoiceProductServiceImplTest {

    @InjectMocks
    InvoiceProductServiceImpl serviceImp;
    @Mock
    InvoiceProductService service;


    @Mock
    InvoiceProductRepository repository;

    @Spy
    PasswordEncoder passwordEncoder;

    @Spy
    static MapperUtil mapperUtil = new MapperUtil(new ModelMapper());

    InvoiceProduct invoiceProduct;
    InvoiceProductDTO invoiceProductDTO;
  Invoice invoice;
  Product product;
  InvoiceDTO invoiceDTO;
  ProductDTO productDTO;
    @BeforeEach
    void setUp(){
       invoiceDTO=new InvoiceDTO();
       invoice=new Invoice();
       product=new Product();
        productDTO=new ProductDTO();
        invoiceDTO.setId(1L);
        productDTO.setId(2L);
        invoice.setId(1L);
        product.setId(2L);
        invoiceProduct=new InvoiceProduct();
        invoiceProduct.setProduct(product);
        invoiceProduct.setInvoice(invoice);
        invoiceProduct.setId(3L);

        invoiceProductDTO=new InvoiceProductDTO();
        invoiceProductDTO.setProduct(productDTO);
        invoiceProductDTO.setInvoice(invoiceDTO);
        invoiceProductDTO.setId(3L);

    }
private List<InvoiceProduct>getInvoiceProductList(){
        InvoiceProduct invoiceProduct2=new InvoiceProduct();
        invoiceProduct2.setId(1L);
        invoiceProduct2.setPrice(BigDecimal.valueOf(100));

        return List.of(invoiceProduct,invoiceProduct2);

}
private List<InvoiceProductDTO>getInvoiceProductListDto(){
    InvoiceProductDTO invoiceProductDto2=new InvoiceProductDTO();
    invoiceProductDto2.setId(1L);
    invoiceProductDto2.setPrice(BigDecimal.valueOf(100));

    return List.of(invoiceProductDTO,invoiceProductDto2);
}



@Test

void should_find_all_by_invoice_id(){
//stub
    when(repository.findAllByInvoice_Id(1L)).thenReturn(getInvoiceProductList());


  List<InvoiceProductDTO> expectedProduct=getInvoiceProductListDto();
    List<InvoiceProductDTO> actualDto=  service.findAllByInvoiceId(1L);



   assertThat(actualDto).usingRecursiveComparison().ignoringActualNullFields().isEqualTo(expectedProduct);

      }

















    @Test
    @DisplayName("When_find_InvoiceProduct_By_id_then_success")
    public void GIVEN_ID_WHEN_FIND_InvoiceProduct_BY_ID_THEN_SUCCESS(){
        // Given
        InvoiceProduct invoice = new InvoiceProduct();
        invoice.setId(2L);
        // When
        when(repository.findById(invoice.getId())).thenReturn(Optional.of(invoice));
        var returnedProduct = service.findById(invoice.getId());
        // Then
        assertThat(returnedProduct.getInvoice().equals(invoice.getInvoice()));
    }
    @Test
    @DisplayName("When_given_non_existing_id_then_fail")
    public void GIVEN_NON_EXISTING_ID_WHEN_FIND_Invoice_BY_ID_THEN_FAIL(){
        when(repository.findById(anyLong())).thenThrow(NoSuchElementException.class); // Mockito return null by itself...
        assertThrows(NoSuchElementException.class, () -> service.findById(anyLong()));
    }
    @Test
    @DisplayName("When_given_null_id_then_fail")
    public void GIVEN_NULL_ID_WHEN_FIND_BY_ID_THEN_FAIL(){
        when(repository.findById(null)).thenThrow(NoSuchElementException.class); // Mockito return null by itself...
        assertThrows(NoSuchElementException.class, () -> service.findById(null));
    }






    @Test
    void findAllByInvoiceId() {
    }

    @Test
    void findById() {
    }

    @Test
    void save() {
    }

    @Test
    void deleteById() {
    }
}