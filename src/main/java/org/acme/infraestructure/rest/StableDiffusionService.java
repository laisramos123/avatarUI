package org.acme.infraestructure.rest;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.domain.model.ProfilePhoto;
import org.acme.infraestructure.rest.client.StableDiffusionRestClient;
import org.apache.commons.io.FileUtils;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.NoSuchElementException;

@ApplicationScoped
public class StableDiffusionService {
    private final StableDiffusionRestClient stableDiffusionRestClient;

    public StableDiffusionService(@RestClient StableDiffusionRestClient stableDiffusionRestClient) {
        this.stableDiffusionRestClient = stableDiffusionRestClient;
    }

    @Transactional
    public Uni<String> generate (ProfilePhoto profilePhoto) throws IOException{
        var fileContent = FileUtils.readFileToByteArray(new File(profilePhoto.originalPhoto()));
        var encondedString = Base64.getEncoder().encodeToString(fileContent);

        return stableDiffusionRestClient.img2img(new StableDiffusionRestClient.StableDiffusionRequest(encondedString))
                .onItem()
                .transform(response -> response.images().stream().findFirst().orElseThrow(NoSuchElementException::new));
    }
}
