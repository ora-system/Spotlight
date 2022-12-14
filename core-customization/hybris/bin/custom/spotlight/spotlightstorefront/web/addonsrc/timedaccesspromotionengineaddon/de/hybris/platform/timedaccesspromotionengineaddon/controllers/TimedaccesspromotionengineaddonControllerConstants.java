/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.timedaccesspromotionengineaddon.controllers;

import de.hybris.platform.acceleratorcms.model.actions.AddToCartActionModel;


/**
 * Timedaccesspromotionengineaddon constants
 */
public interface TimedaccesspromotionengineaddonControllerConstants
{
	String ADDON_PREFIX = "addon:/timedaccesspromotionengineaddon";

	interface Actions
	{
		interface Cms
		{
			String _Prefix = "/view/";
			String _Suffix = "Controller";

			/**
			 * Customized AddToCartAction controller
			 */
			String AddToCartAction = _Prefix + AddToCartActionModel._TYPECODE + _Suffix;

		}
	}
}
