package service.Implementation;

import exceptions.ConstraintViolationException;
import model.FactOverride;
import model.Thing;
import model.dto.FactOverrideDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.FactOverrideRepository;
import repository.ThingRepository;
import service.FactOverrideService;

import java.util.List;

@Service
public class FactOverrideServiceImpl implements FactOverrideService {

    private static final String ERROR_MSG_SAVE = "Cannot save a fact about override thing price";

    @Autowired
    private FactOverrideRepository factOverrideRepository;

    @Autowired
    private ThingRepository thingRepository;

    public FactOverride save(FactOverride factOverride) throws ConstraintViolationException {
        try{
            return factOverrideRepository.saveAndFlush(factOverride);
        } catch (Exception ex){
            throw new ConstraintViolationException(ERROR_MSG_SAVE);
        }
    }

    public List<FactOverrideDto> getDataOfThing(Integer thingId) {
        return factOverrideRepository.findAllByThingId(thingId);
    }

    public List<Thing> getOverridesByUser(Integer userId) {
        return thingRepository.getUserBets(userId);
    }
}
