package io.arpaul.ytspringbatch.batch;

import io.arpaul.ytspringbatch.payload.User;
import io.arpaul.ytspringbatch.payload.UserAddress;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class ProcessorUserAddress implements ItemProcessor<UserAddress, UserAddress> {

    private  static final Map<String, String> DEPT_NAMES = new HashMap<>();

    public ProcessorUserAddress() {

    }

    @Override
    public UserAddress process(UserAddress userAddress) throws Exception {

        userAddress.setTime(new Date());
        System.out.println(String.format("User %s %s %s stays in %s",
                userAddress.getFirst_name(),
                userAddress.getMiddle_initial(),
                userAddress.getLast_name(),
                userAddress.getState()));
        return userAddress;
    }
}
