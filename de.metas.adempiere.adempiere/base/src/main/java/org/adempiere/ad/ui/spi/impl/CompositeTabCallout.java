package org.adempiere.ad.ui.spi.impl;

/*
 * #%L
 * de.metas.adempiere.adempiere.base
 * %%
 * Copyright (C) 2015 metas GmbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */


import java.util.ArrayList;
import java.util.List;

import org.adempiere.ad.callout.api.ICalloutRecord;
import org.adempiere.ad.ui.spi.ITabCallout;
import org.adempiere.util.Check;
import org.adempiere.util.lang.ObjectUtils;
import org.slf4j.Logger;

import de.metas.logging.LogManager;

public class CompositeTabCallout implements ITabCallout
{
	// services
	private final transient Logger logger = LogManager.getLogger(getClass());

	private final List<ITabCallout> tabCalloutsAll = new ArrayList<>();
	/** Initialized tab callouts */
	private final List<ITabCallout> tabCallouts = new ArrayList<>();
	private boolean _initialized = false;

	public void addTabCallout(final ITabCallout tabCallout)
	{
		assertNotInitialized();
		Check.assumeNotNull(tabCallout, "tabCallout not null");

		if (tabCalloutsAll.contains(tabCallout))
		{
			return;
		}
		tabCalloutsAll.add(tabCallout);
	}

	private final void assertNotInitialized()
	{
		Check.assume(!_initialized, "not already initialized");
	}

	private final void markAsInitialized()
	{
		assertNotInitialized();
		_initialized = true;
	}

	@Override
	public String toString()
	{
		return ObjectUtils.toString(this);
	}

	@Override
	public void onInit(final ICalloutRecord calloutRecord)
	{
		markAsInitialized();

		for (final ITabCallout tabCallout : tabCalloutsAll)
		{
			try
			{
				tabCallout.onInit(calloutRecord);
				tabCallouts.add(tabCallout);
			}
			catch (Exception e)
			{
				logger.error("Failed to initialize: " + tabCallout, e);
			}
		}
	}

	@Override
	public void onIgnore(final ICalloutRecord calloutRecord)
	{
		for (final ITabCallout tabCallout : tabCallouts)
		{
			tabCallout.onIgnore(calloutRecord);
		}
	}

	@Override
	public void onNew(final ICalloutRecord calloutRecord)
	{
		for (final ITabCallout tabCallout : tabCallouts)
		{
			tabCallout.onNew(calloutRecord);
		}
	}

	@Override
	public void onSave(final ICalloutRecord calloutRecord)
	{
		for (final ITabCallout tabCallout : tabCallouts)
		{
			tabCallout.onSave(calloutRecord);
		}
	}

	@Override
	public void onDelete(final ICalloutRecord calloutRecord)
	{
		for (final ITabCallout tabCallout : tabCallouts)
		{
			tabCallout.onDelete(calloutRecord);
		}
	}

	@Override
	public void onRefresh(final ICalloutRecord calloutRecord)
	{
		for (final ITabCallout tabCallout : tabCallouts)
		{
			tabCallout.onRefresh(calloutRecord);
		}
	}

	@Override
	public void onRefreshAll(final ICalloutRecord calloutRecord)
	{
		for (final ITabCallout tabCallout : tabCallouts)
		{
			tabCallout.onRefreshAll(calloutRecord);
		}
	}

	@Override
	public void onAfterQuery(final ICalloutRecord calloutRecord)
	{
		for (final ITabCallout tabCallout : tabCallouts)
		{
			tabCallout.onAfterQuery(calloutRecord);
		}
	}

}
