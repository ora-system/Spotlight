package au.com.spotlight.core.job;

import au.com.spotlight.core.model.TopSellerCronjobModel;
import au.com.spotlight.core.topseller.service.TopSellerService;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.adaptivesearch.model.AsPromotedItemModel;
import de.hybris.platform.adaptivesearch.model.AsSimpleSearchConfigurationModel;
import de.hybris.platform.adaptivesearch.model.AsSimpleSearchProfileModel;
import de.hybris.platform.adaptivesearch.services.AsConfigurationService;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.PK;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.textfieldconfiguratortemplateservices.model.TextFieldConfiguratorSettingModel;
import org.apache.commons.collections.CollectionUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class TopSellerPerformableJobTest {

    @Mock
    private TopSellerService topSellerService;

    @Mock
    private ModelService modelService;

    @Mock
    private AsConfigurationService asConfigurationService;

    @Spy
    @InjectMocks
    private final TopSellerPerformableJob job = new TopSellerPerformableJob();

    @Mock
    private TopSellerCronjobModel topSellerCronjobModel;

    @Mock
    private BaseSiteModel baseSiteModel;

    @Mock
    private AsSimpleSearchProfileModel searchProfile;

    @Mock
    private AsSimpleSearchConfigurationModel configurationModel;

    @Mock
    private ProductModel prd1;

    @Mock
    private ProductModel prd2;
    @Mock
    private AsPromotedItemModel promoted;

    @Mock
    private PK pk;

    @Before
    public void setUp() throws Exception {
        topSellerCronjobModel.setBaseSite(baseSiteModel);
        topSellerCronjobModel.setSearchProfile(searchProfile);
    }

    @Test
    public void performWithResults() {
        when(topSellerService.getTopSellingProducts(baseSiteModel)).thenReturn(Arrays.asList(prd1,prd2));
        when(searchProfile.getSearchConfigurations()).thenReturn(Collections.singletonList(configurationModel));
        when(configurationModel.getPromotedItems()).thenReturn(Collections.emptyList());
        when(topSellerCronjobModel.getSearchProfile()).thenReturn(searchProfile);
        when(topSellerCronjobModel.getBaseSite()).thenReturn(baseSiteModel);
        when(asConfigurationService.createConfiguration(any())).thenReturn(promoted);
        when(configurationModel.getPk()).thenReturn(mock(PK.class));
        when(prd1.getPk()).thenReturn(mock(PK.class));
        when(prd2.getPk()).thenReturn(mock(PK.class));

        job.perform(topSellerCronjobModel);
        verify(asConfigurationService,times(2)).saveConfiguration(any());
    }
}