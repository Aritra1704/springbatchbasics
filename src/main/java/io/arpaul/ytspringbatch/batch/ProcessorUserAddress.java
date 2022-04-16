package io.arpaul.ytspringbatch.batch;

import io.arpaul.ytspringbatch.payload.User;
import io.arpaul.ytspringbatch.payload.UserAddress;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class ProcessorUserAddress implements ItemProcessor<UserAddress, UserAddress> {
    private static int count = 0;
    public ProcessorUserAddress() {
    }

    @Override
    public UserAddress process(UserAddress userAddress) throws Exception {
        userAddress.setTime(new Date());
        return userAddress;
    }
}
