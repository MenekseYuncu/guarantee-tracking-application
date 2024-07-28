package org.menekseyuncu.guaranteetrackingapplication.warranty.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.menekseyuncu.guaranteetrackingapplication.common.model.mapper.BaseMapper;
import org.menekseyuncu.guaranteetrackingapplication.warranty.controller.response.WarrantyResponse;
import org.menekseyuncu.guaranteetrackingapplication.warranty.model.domain.Warranty;

/**
 * Mapper interface for converting {@link Warranty} entities to {@link WarrantyResponse} DTOs.
 */
@Mapper
public interface WarrantyToWarrantyResponseMapper extends BaseMapper<Warranty, WarrantyResponse> {

    WarrantyToWarrantyResponseMapper INSTANCE = Mappers.getMapper(WarrantyToWarrantyResponseMapper.class);
}