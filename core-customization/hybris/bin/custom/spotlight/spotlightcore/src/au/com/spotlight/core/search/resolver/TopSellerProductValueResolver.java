package au.com.spotlight.core.search.resolver;

import au.com.spotlight.core.topseller.service.TopSellerService;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.indexer.IndexerBatchContext;
import de.hybris.platform.solrfacetsearch.indexer.spi.InputDocument;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractValueResolver;

/**
 * @author SindhuSayana
 */
public class TopSellerProductValueResolver extends AbstractValueResolver<ProductModel, Object, Object> {

    private TopSellerService topSellerService;
    /**
     * @param inputDocument
     * @param indexerBatchContext
     * @param indexedProperty
     * @param productModel
     * @param valueResolverContext
     * @throws FieldValueProviderException
     */
    @Override
    protected void addFieldValues(InputDocument inputDocument, IndexerBatchContext indexerBatchContext, IndexedProperty indexedProperty, ProductModel productModel, ValueResolverContext<Object, Object> valueResolverContext) throws FieldValueProviderException {
        Long count = getTopSellerService().getTopQuantityForProduct(productModel, indexerBatchContext.getFacetSearchConfig().getIndexConfig().getBaseSite());
        inputDocument.addField(indexedProperty, count);
    }

    public TopSellerService getTopSellerService() {
        return topSellerService;
    }

    public void setTopSellerService(TopSellerService topSellerService) {
        this.topSellerService = topSellerService;
    }
}
