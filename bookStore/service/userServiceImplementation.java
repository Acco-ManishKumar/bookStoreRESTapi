package com.bookStore.bookStore.service;

import com.bookStore.bookStore.exceptions.customException;
import com.bookStore.bookStore.model.user;
import com.bookStore.bookStore.model.walletTransaction;
import com.bookStore.bookStore.repository.userRepo;
import com.bookStore.bookStore.repository.walletTransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class userServiceImplementation implements userService {

    @Autowired
    private userRepo userRepo1;

    @Autowired
    private walletTransactionRepo walletTransactionRepo1;

    @Override
    public user createUser(user user1){
        return userRepo1.save(user1);
    }

    @Override
    public List<user> getUsers(){
        return userRepo1.findAll();
    }

    @Override
    public user updateUser(user user1){
        Optional<user> userObj = this.userRepo1.findById(user1.getUserId());

        if(userObj.isPresent()){

            user userUpdate = userObj.get();
            userUpdate.setUserName(user1.getUserName());
            userUpdate.setUserEmail(user1.getUserEmail());
            userUpdate.setUserMobile(user1.getUserMobile());
            return this.userRepo1.save(userUpdate);
        }
        else {
            throw new customException("User not available with provided user ID :"+user1.getUserId());
        }
    }

    boolean checkAmount(Double amount){

        if(amount>0 && amount%500 == 0){
            return true;
        }
        else {
            throw new customException("Amount is not multiple of 500 or negative");
        }
    }

    @Override
    public user addMoneyToWallet(Integer userid, Double amount){
        Optional<user> userObj = userRepo1.findById(userid);

        if(userObj.isPresent()){
            user depositUser = userObj.get();

            if(checkAmount(amount)){
                walletTransaction newWallet = new walletTransaction();
                newWallet.setUserId(depositUser.getUserId());
                newWallet.setTransaction("+"+amount);
                walletTransactionRepo1.save(newWallet);
                depositUser.setWallet((amount+depositUser.getWallet()));
                return this.userRepo1.save(depositUser);
            }
            else {
                throw new customException("This user does not exist : " +depositUser.getUserId() +depositUser.getUserName());
            }
        }
        else {
            throw new customException("User not available with provided user ID :"+userid);
        }
    }

    @Override
    public user suspendUser(Integer userId){
        Optional<user> userObj = userRepo1.findById(userId);

        if(userObj.isPresent()){
            user suspendUser = userObj.get();

            if(suspendUser.getStrikes() == 3){
                suspendUser.setStatus("Disabled");
                return this.userRepo1.save(suspendUser);
            }
            else{
                throw new customException("Strike count : " +suspendUser.getStrikes());
            }
        }
        else {
            throw new customException("User not available with provided user ID :"+userId);
        }
    }

}
