/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 31 July 2022, 1:28:28 pm                    ---
 * ----------------------------------------------------------------
 */
package au.com.spotlight.core.jalo;

import au.com.spotlight.core.constants.SpotlightCoreConstants;
import de.hybris.platform.adaptivesearch.jalo.AsSimpleSearchProfile;
import de.hybris.platform.basecommerce.jalo.site.BaseSite;
import de.hybris.platform.cronjob.jalo.CronJob;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.cronjob.jalo.CronJob TopSellerCronjob}.
 */
@SuppressWarnings({"deprecation","unused","cast"})
public abstract class GeneratedTopSellerCronjob extends CronJob
{
	/** Qualifier of the <code>TopSellerCronjob.searchProfile</code> attribute **/
	public static final String SEARCHPROFILE = "searchProfile";
	/** Qualifier of the <code>TopSellerCronjob.baseSite</code> attribute **/
	public static final String BASESITE = "baseSite";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(CronJob.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(SEARCHPROFILE, AttributeMode.INITIAL);
		tmp.put(BASESITE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TopSellerCronjob.baseSite</code> attribute.
	 * @return the baseSite
	 */
	public BaseSite getBaseSite(final SessionContext ctx)
	{
		return (BaseSite)getProperty( ctx, BASESITE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TopSellerCronjob.baseSite</code> attribute.
	 * @return the baseSite
	 */
	public BaseSite getBaseSite()
	{
		return getBaseSite( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TopSellerCronjob.baseSite</code> attribute. 
	 * @param value the baseSite
	 */
	public void setBaseSite(final SessionContext ctx, final BaseSite value)
	{
		setProperty(ctx, BASESITE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TopSellerCronjob.baseSite</code> attribute. 
	 * @param value the baseSite
	 */
	public void setBaseSite(final BaseSite value)
	{
		setBaseSite( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TopSellerCronjob.searchProfile</code> attribute.
	 * @return the searchProfile
	 */
	public AsSimpleSearchProfile getSearchProfile(final SessionContext ctx)
	{
		return (AsSimpleSearchProfile)getProperty( ctx, SEARCHPROFILE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TopSellerCronjob.searchProfile</code> attribute.
	 * @return the searchProfile
	 */
	public AsSimpleSearchProfile getSearchProfile()
	{
		return getSearchProfile( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TopSellerCronjob.searchProfile</code> attribute. 
	 * @param value the searchProfile
	 */
	public void setSearchProfile(final SessionContext ctx, final AsSimpleSearchProfile value)
	{
		setProperty(ctx, SEARCHPROFILE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TopSellerCronjob.searchProfile</code> attribute. 
	 * @param value the searchProfile
	 */
	public void setSearchProfile(final AsSimpleSearchProfile value)
	{
		setSearchProfile( getSession().getSessionContext(), value );
	}
	
}
