package au.com.spotlight.core.topseller.dao.impl;

import au.com.spotlight.core.topseller.dao.TopSellerDao;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.internal.dao.Dao;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 * @author SindhuSayana
 */
public class DefaultTopSellerDao extends DefaultGenericDao<ProductModel> implements TopSellerDao, Dao {


    private static final String TOP_SELLER_QUERY = "SELECT {oe." + OrderEntryModel.PRODUCT + "} FROM {" + OrderEntryModel._TYPECODE +
            " as oe JOIN " + OrderModel._TYPECODE + " as o ON {oe." + OrderEntryModel.ORDER + "} = {o.pk}} WHERE {oe." + OrderEntryModel.CREATIONTIME + "}>=?date AND {o." + OrderModel.SITE + "}=?site " +
            " GROUP BY {oe." + OrderEntryModel.PRODUCT + "} ORDER BY SUM({oe." + OrderEntryModel.QUANTITY + "}) desc";

    private static final String TOP_SELLER_PRODUCT_QUERY = "SELECT {oe." + OrderEntryModel.PRODUCT + "} FROM {" + OrderEntryModel._TYPECODE +
            " as oe JOIN " + OrderModel._TYPECODE + " as o ON {oe." + OrderEntryModel.ORDER + "} = {o.pk}} WHERE {oe." +
            OrderEntryModel.CREATIONTIME + "}>=?date AND {oe." + OrderEntryModel.PRODUCT + "}=?product  AND {o." + OrderModel.SITE + "}=?site ";

    public DefaultTopSellerDao(String typecode) {
        super(typecode);
    }

    /**
     * @param baseSiteModel
     * @return
     */
    @Override
    public List<ProductModel> getTopSellingProducts(BaseSiteModel baseSiteModel) {

        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, -24);//Calculate time before 24 hours from now
        final FlexibleSearchQuery query = new FlexibleSearchQuery(TOP_SELLER_QUERY);
        query.setResultClassList(Collections.singletonList(ProductModel.class));
        query.addQueryParameter("date", cal.getTime());
        query.addQueryParameter("site",baseSiteModel);
        query.setCount(100);
        final SearchResult results = getFlexibleSearchService().search(query);
        return results.getResult();
    }

    /**
     * @param productModel
     * @param baseSiteModel
     * @return
     */
    @Override
    public Long getTopQuantityForProduct(ProductModel productModel, BaseSiteModel baseSiteModel) {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, -24);//Calculate time before 24 hours from now
        final FlexibleSearchQuery query = new FlexibleSearchQuery(TOP_SELLER_PRODUCT_QUERY);
        final List<Class> resultClasses = new ArrayList<>();
        resultClasses.add(Long.class);
        query.addQueryParameter("date", cal.getTime());
        query.addQueryParameter("site", baseSiteModel);
        query.addQueryParameter("product", productModel);
        query.setResultClassList(resultClasses);
        if (getFlexibleSearchService().search(query).getResult().iterator().hasNext()) {
            return (Long) getFlexibleSearchService().search(query).getResult().iterator().next();
        }
        return Long.valueOf(0);
    }
}
