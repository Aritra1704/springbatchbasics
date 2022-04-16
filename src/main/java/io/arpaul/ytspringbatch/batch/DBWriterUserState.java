package io.arpaul.ytspringbatch.batch;

import io.arpaul.ytspringbatch.payload.UserState;
import io.arpaul.ytspringbatch.repository.UserStateRepository;
import io.arpaul.ytspringbatch.utils.Constants;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DBWriterUserState implements ItemWriter<UserState> {

    private  static final Set<String> states = new HashSet<>();

    @Autowired
    private UserStateRepository userStateRepository;


    @Override
    public void write(List<? extends UserState> userStates) throws Exception {
        Map<String, UserState> resultStates = new HashMap<>();
        for (UserState state: userStates) {
            if(!states.contains(state.getState_id())) {
                states.add(state.getState_id());
                state.setNo_users(1);
                resultStates.put(state.getState_id(), state);
            } else {
                UserState prvsData = resultStates.get(state.getState_id());
                prvsData.setNo_users(prvsData.getNo_users() + 1);
            }
        }
        userStateRepository.saveAll(new ArrayList<>(resultStates.values()));
    }
}
