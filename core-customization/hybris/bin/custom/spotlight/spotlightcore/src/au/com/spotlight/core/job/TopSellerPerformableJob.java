package au.com.spotlight.core.job;

import au.com.spotlight.core.model.TopSellerCronjobModel;
import au.com.spotlight.core.topseller.service.TopSellerService;
import de.hybris.platform.adaptivesearch.model.AsPromotedItemModel;
import de.hybris.platform.adaptivesearch.model.AsSimpleSearchConfigurationModel;
import de.hybris.platform.adaptivesearch.model.AsSimpleSearchProfileModel;
import de.hybris.platform.adaptivesearch.services.AsConfigurationService;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.model.ModelService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

/**
 * TopSellers JOB to prepare the list of top products based on the order history
 *
 * @author SindhuSayana
 */
public class TopSellerPerformableJob extends AbstractJobPerformable<TopSellerCronjobModel> {

    private static final Logger LOG = Logger.getLogger(TopSellerPerformableJob.class);

    private TopSellerService topSellerService;

    private ModelService modelService;

    private AsConfigurationService asConfigurationService;

    /**
     * @param topSellerCronjobModel
     * @return
     */
    @Override
    public PerformResult perform(final TopSellerCronjobModel topSellerCronjobModel) {
        validateParameterNotNull(topSellerCronjobModel.getSearchProfile(), "SearchProfile must not be null!");
        validateParameterNotNull(topSellerCronjobModel.getBaseSite(), "BaseSite must not be null!");

        if (LOG.isDebugEnabled()) {
            LOG.debug("###### Start TopSeller product JOB ###### ");
        }

        final List<ProductModel> topProducts = getTopSellerService().getTopSellingProducts(topSellerCronjobModel.getBaseSite());
        final AsSimpleSearchProfileModel searchProfile = topSellerCronjobModel.getSearchProfile();
        final List<AsSimpleSearchConfigurationModel> searchConfigurations = searchProfile.getSearchConfigurations();
        if (CollectionUtils.isNotEmpty(searchConfigurations)) {
            searchConfigurations.forEach(config -> {
                if (CollectionUtils.isNotEmpty(config.getPromotedItems())) {
                    getModelService().removeAll(config.getPromotedItems());
                }
                config.setPromotedItems(topProducts.stream().map(productModel -> createPromotedItem(productModel, config)).collect(Collectors.toList()));
            });
        }
        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }

    /**
     * @param productModel
     * @param config
     * @return
     */
    private AsPromotedItemModel createPromotedItem(final ProductModel productModel, final AsSimpleSearchConfigurationModel config) {
        final AsPromotedItemModel promotedItemModel = asConfigurationService.createConfiguration(AsPromotedItemModel.class);
        promotedItemModel.setSearchConfiguration(config);
        promotedItemModel.setUid(UUID.randomUUID().toString());
        promotedItemModel.setCatalogVersion(config.getCatalogVersion());
        promotedItemModel.setItem(productModel);
        promotedItemModel.setUniqueIdx(config.getPk().toString() + "_" + productModel.getPk().toString());
        getAsConfigurationService().saveConfiguration(promotedItemModel);
        return promotedItemModel;
    }

    public TopSellerService getTopSellerService() {
        return topSellerService;
    }

    public void setTopSellerService(TopSellerService topSellerService) {
        this.topSellerService = topSellerService;
    }

    public ModelService getModelService() {
        return modelService;
    }

    @Override
    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    public AsConfigurationService getAsConfigurationService() {
        return asConfigurationService;
    }

    public void setAsConfigurationService(AsConfigurationService asConfigurationService) {
        this.asConfigurationService = asConfigurationService;
    }
}
