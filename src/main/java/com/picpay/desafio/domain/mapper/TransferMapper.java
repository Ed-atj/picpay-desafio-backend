package com.picpay.desafio.domain.mapper;

import com.picpay.desafio.domain.dto.TransferDto;
import com.picpay.desafio.domain.entity.Transfer;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = UserMapperHelper.class)
public interface TransferMapper {


    @Mapping(source = "sender", target = "sender", qualifiedByName = "userToId")
    @Mapping(source = "receiver", target = "receiver", qualifiedByName = "userToId")
    TransferDto toDto(Transfer transfer);

    @Mapping(source = "sender", target = "sender", qualifiedByName = "idToUser")
    @Mapping(source = "receiver", target = "receiver", qualifiedByName = "idToUser")
    Transfer toTransfer(TransferDto transferDto, @Context UserMapperHelper helper);

}

