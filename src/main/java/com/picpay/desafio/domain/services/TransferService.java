package com.picpay.desafio.domain.services;

import com.picpay.desafio.domain.dto.TransferDto;
import com.picpay.desafio.domain.entity.Transfer;
import com.picpay.desafio.domain.entity.User;
import com.picpay.desafio.domain.exception.TransferReceiverNotFoundException;
import com.picpay.desafio.domain.exception.TransferSenderNotFoundException;
import com.picpay.desafio.domain.exception.UserNotEnoughBalanceException;
import com.picpay.desafio.domain.exception.UserTypeTransferNotAllowedException;
import com.picpay.desafio.domain.mapper.TransferMapper;
import com.picpay.desafio.domain.mapper.UserMapper;
import com.picpay.desafio.domain.repositories.TransferRepository;
import com.picpay.desafio.domain.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferService {

    public static final Logger logger = LoggerFactory.getLogger(TransferService.class);
    private final TransferRepository transferRepository;
    private final AuthorizationService authorizationService;
    private final NotificationService notificationService;
    private final UserRepository userRepository;
    private final TransferMapper transferMapper;
    private final UserMapper userMapper;

    @Transactional
    public TransferDto transfer(TransferDto transferDto){

        logger.info("Starting transfer from {} to {} for amount {}",
            transferDto.sender(),
            transferDto.receiver(),
            transferDto.value());


        var sender = userRepository.findById(transferDto.sender()).orElseThrow(
            () -> new TransferSenderNotFoundException(transferDto.sender()));
        var receiver = userRepository.findById(transferDto.receiver()).orElseThrow(
            () -> new TransferReceiverNotFoundException(transferDto.receiver()));

        validateTransfer(transferDto, sender);


        sender.debit(transferDto.value());
        receiver.credit(transferDto.value());
        var transfer = new Transfer(sender, receiver, transferDto.value());

        userRepository.save(sender);
        userRepository.save(receiver);
        var transferResult = transferRepository.save(transfer);

        var transferReturn = transferMapper.toDto(transferResult);

        notificationService.sendNotification(transferReturn)
            .subscribe(
                response -> logger.info("Notification sent successfully: {}", response),
                e -> logger.error("Error while sending notification.", e)
            );
        return transferReturn;
    }

    public void validateTransfer(TransferDto transferDto, User user){
        if(user.blockShopkeeperTransfer()){
            throw new UserTypeTransferNotAllowedException();
        }
        if(user.userNotEnoughBalance(transferDto.value())){
            throw new UserNotEnoughBalanceException();
        }

    }
}
