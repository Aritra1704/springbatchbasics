package io.arpaul.ytspringbatch.batch;

import io.arpaul.ytspringbatch.payload.UserAddress;
import io.arpaul.ytspringbatch.repository.UserAddressRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBWriterUserAddress implements ItemWriter<UserAddress> {

    @Autowired
    private UserAddressRepository userAddressRepository;

    @Override
    public void write(List<? extends UserAddress> userAddresses) throws Exception {
        System.out.println("Dta saved for userAddresses: " + userAddresses);
        userAddressRepository.saveAll(userAddresses);
    }
}
