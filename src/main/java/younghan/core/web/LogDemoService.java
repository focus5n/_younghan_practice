package younghan.core.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import younghan.core.common.MyLogger;

@Service
@RequiredArgsConstructor
public class LogDemoService {

    private final MyLogger myLogger;

    public void logic(String test_id) {
        myLogger.log("myService ID = " + test_id);
    }
}
