package Service;

import Domain.Smoke;
import Domain.Temperature;
import Repository.Repository;
import Validators.SmokeValidator;
import Validators.TempValidator;

public class Service {
    private Repository<Double, Temperature> repoTemp;
    private Repository<Double, Smoke> repoSmoke;

    private final TempValidator tempValidator;
    private final SmokeValidator smokeValidator;

    public Service(TempValidator tempValidator, SmokeValidator smokeValidator, Repository<Double, Smoke> repoSmoke, Repository<Double, Temperature> repoTemp) {
        this.tempValidator = tempValidator;
        this.smokeValidator = smokeValidator;
        this.repoSmoke = repoSmoke;
        this.repoTemp = repoTemp;
    }

    public void addTemp(Double last_temperature, int diameter) {
        Temperature temp = new Temperature(last_temperature, diameter);
        tempValidator.validateTemp(temp);
        repoTemp.add(last_temperature, temp);
    }

    public void addSmoke(Double last_temperature, int length) {
        Smoke temp = new Smoke(last_temperature, length);
        smokeValidator.validateSmoke(temp);
        repoSmoke.add(last_temperature, temp);
    }

}
