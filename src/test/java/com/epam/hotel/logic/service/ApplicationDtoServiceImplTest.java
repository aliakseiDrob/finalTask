package com.epam.hotel.logic.service;

import com.epam.hotel.dao.DaoHelper;
import com.epam.hotel.dao.DaoHelperFactory;
import com.epam.hotel.dao.api.ApplicationDtoDao;
import com.epam.hotel.entity.dto.ApplicationDto;
import com.epam.hotel.entity.enums.RoomType;
import com.epam.hotel.exception.DaoException;
import com.epam.hotel.exception.ServicesException;
import com.epam.hotel.logic.service.api.ApplicationDtoService;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class ApplicationDtoServiceImplTest {
    private static final long INVOICES_ROW_COUNT_IN_TABLE = 9L;
    private static final int DEFAULT_ITEMS_PER_PAGE = 7;
    private static final int INVOICES_AMOUNT_PAGES = 2;
    private static final int BEGIN_POSITION_SEARCH_IN_TABLE = 0;
    private static final int DEFAULT_PAGE = 1;
    private static final long USER_ID = 1L;
    private static final long APPLICATION_DTO_ID = 1L;
    private final ApplicationDto firstInvoice = new ApplicationDto(APPLICATION_DTO_ID, LocalDate.now(), LocalDate.now(), RoomType.COMFORT, (byte) 4, new BigDecimal("123"), 11);
    private final ApplicationDto secondInvoice = new ApplicationDto(APPLICATION_DTO_ID, LocalDate.now(), LocalDate.now(), RoomType.COMFORT, (byte) 2, new BigDecimal("217"), 17);
    private final List<ApplicationDto> EXPECTED_LIST = Arrays.asList(firstInvoice, secondInvoice);
    private final DaoHelper daoHelper = mock(DaoHelper.class);
    private final DaoHelperFactory daoHelperFactory = mock(DaoHelperFactory.class);
    private final ApplicationDtoDao applicationDtoDao = mock(ApplicationDtoDao.class);
    private final ApplicationDtoService applicationDtoService = new ApplicationDtoServiceImpl(daoHelperFactory);

    @BeforeMethod
    public void setUp() throws DaoException {
        when(daoHelperFactory.create()).thenReturn(daoHelper);
        when(daoHelper.createApplicationDtoDao()).thenReturn(applicationDtoDao);
    }

    @Test
    public void testFindAmountPagesShouldReturnAmountPagesInvoices() throws DaoException, ServicesException {
        when(applicationDtoDao.findRowCount()).thenReturn(INVOICES_ROW_COUNT_IN_TABLE);

        long actualAmountPages = applicationDtoService.findAmountPages(DEFAULT_ITEMS_PER_PAGE);

        verify(applicationDtoDao, times(1)).findRowCount();
        Assert.assertEquals(actualAmountPages, INVOICES_AMOUNT_PAGES);
    }

    @Test
    public void testFindPaginatePageInvoicesShouldReturnListInvoices() throws DaoException, ServicesException {
        when(applicationDtoDao.findPaginatePage(DEFAULT_ITEMS_PER_PAGE, BEGIN_POSITION_SEARCH_IN_TABLE)).thenReturn(EXPECTED_LIST);

        List<ApplicationDto> actualList = applicationDtoService.findPaginatePageInvoices(DEFAULT_ITEMS_PER_PAGE, DEFAULT_PAGE);

        Assert.assertEquals(actualList, EXPECTED_LIST);
    }

    @Test
    public void testFindAmountPagesByUserIdShouldReturnAmountPagesInvoicesByUserId() throws DaoException, ServicesException {
        when(applicationDtoDao.findRowCountByUserId(USER_ID)).thenReturn(INVOICES_ROW_COUNT_IN_TABLE);
        long actualAmountPages = applicationDtoService.findAmountPagesByUserId(USER_ID, DEFAULT_ITEMS_PER_PAGE);

        verify(applicationDtoDao, times(1)).findRowCountByUserId(USER_ID);
        Assert.assertEquals(actualAmountPages, INVOICES_AMOUNT_PAGES);

    }

    @Test
    public void testFindPaginatePageByUserIdInvoicesShouldReturnListInvoicesByUserId() throws DaoException, ServicesException {
        when(applicationDtoDao.findPaginatePageByUserId(USER_ID, DEFAULT_ITEMS_PER_PAGE, BEGIN_POSITION_SEARCH_IN_TABLE)).thenReturn(EXPECTED_LIST);

        List<ApplicationDto> actualList = applicationDtoService.findPaginatePageByUserId(USER_ID, DEFAULT_ITEMS_PER_PAGE, DEFAULT_PAGE);

        Assert.assertEquals(actualList, EXPECTED_LIST);
    }
}