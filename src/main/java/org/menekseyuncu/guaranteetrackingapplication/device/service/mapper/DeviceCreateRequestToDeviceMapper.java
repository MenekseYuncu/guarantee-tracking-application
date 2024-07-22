package org.menekseyuncu.guaranteetrackingapplication.device.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.menekseyuncu.guaranteetrackingapplication.common.model.mapper.BaseMapper;
import org.menekseyuncu.guaranteetrackingapplication.device.controller.request.DeviceCreateRequest;
import org.menekseyuncu.guaranteetrackingapplication.device.model.domain.Device;

@Mapper
public interface DeviceCreateRequestToDeviceMapper extends BaseMapper<DeviceCreateRequest, Device> {

    DeviceCreateRequestToDeviceMapper INSTANCE = Mappers.getMapper(DeviceCreateRequestToDeviceMapper.class);
}
