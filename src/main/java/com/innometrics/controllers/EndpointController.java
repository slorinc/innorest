package com.innometrics.controllers;


import com.innometrics.model.CounterResponseDTO;
import com.innometrics.model.ListResponseDTO;
import com.innometrics.repository.CounterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Created by s_lor_000 on 10/7/2015.
 */
@RestController
@RequestMapping("/internal/counter")
public class EndpointController {

    private final static Logger LOG = LoggerFactory.getLogger(EndpointController.class);

    @Autowired
    private CounterRepository counterRepository;

    @RequestMapping(value = "/{counterName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CounterResponseDTO getCurrentValue(@PathVariable String counterName) {
        if (!counterRepository.getCounterMap().containsKey(counterName)) {
            throw new IllegalArgumentException("The 'counterName' parameter does not exits in our database.");
        }
        return new CounterResponseDTO(counterName, counterRepository.getCounterMap().get(counterName).get());
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ListResponseDTO getAllValues() {
        List<String> responseList = new ArrayList<>();
        responseList.addAll(counterRepository.getCounterMap().keySet());
        return new ListResponseDTO(responseList);
    }

    @RequestMapping(value = "/{counterName}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public CounterResponseDTO incrementCounter(@PathVariable String counterName) {
        if (!counterRepository.getCounterMap().containsKey(counterName)) {
            LOG.info("Created new counter '{}'.",counterName);
            counterRepository.getCounterMap().put(counterName, new AtomicInteger(0));
        }
        return new CounterResponseDTO(counterName, counterRepository.getCounterMap().get(counterName).incrementAndGet());
    }

}
