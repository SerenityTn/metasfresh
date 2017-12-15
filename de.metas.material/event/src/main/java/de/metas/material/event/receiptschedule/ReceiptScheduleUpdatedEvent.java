package de.metas.material.event.receiptschedule;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import de.metas.material.event.commons.EventDescriptor;
import de.metas.material.event.commons.MaterialDescriptor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/*
 * #%L
 * metasfresh-material-event
 * %%
 * Copyright (C) 2017 metas GmbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class ReceiptScheduleUpdatedEvent extends AbstractReceiptScheduleEvent
{
	public static final String TYPE = "ReceiptScheduleUpdatedEvent";

	// TODO: fire this event for shipment schedules, all with deltas etc
	private final BigDecimal orderedQuantityDelta;

	private final BigDecimal reservedQuantityDelta;

	@JsonCreator
	@Builder
	public ReceiptScheduleUpdatedEvent(
			@JsonProperty("eventDescriptor") final EventDescriptor eventDescriptor,
			@JsonProperty("orderedMaterial") final MaterialDescriptor orderedMaterial,
			@JsonProperty("orderedQuantityDelta") @NonNull final BigDecimal orderedQuantityDelta,
			@JsonProperty("reservedQuantity") final BigDecimal reservedQuantity,
			@JsonProperty("reservedQuantityDelta") @NonNull final BigDecimal reservedQuantityDelta,
			@JsonProperty("receiptScheduleId") final int receiptScheduleId)
	{
		super(
				eventDescriptor,
				orderedMaterial,
				reservedQuantity,
				receiptScheduleId);

		this.orderedQuantityDelta = orderedQuantityDelta;
		this.reservedQuantityDelta = reservedQuantityDelta;
	}
}
