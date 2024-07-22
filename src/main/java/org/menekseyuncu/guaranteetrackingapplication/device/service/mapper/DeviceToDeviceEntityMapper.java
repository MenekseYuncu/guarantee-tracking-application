package org.menekseyuncu.guaranteetrackingapplication.device.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.menekseyuncu.guaranteetrackingapplication.common.model.mapper.BaseMapper;
import org.menekseyuncu.guaranteetrackingapplication.device.model.DeviceEntity;
import org.menekseyuncu.guaranteetrackingapplication.device.model.domain.Device;

@Mapper
public interface DeviceToDeviceEntityMapper extends BaseMapper<Device, DeviceEntity> {

    DeviceToDeviceEntityMapper INSTANCE = Mappers.getMapper(DeviceToDeviceEntityMapper.class);
}
