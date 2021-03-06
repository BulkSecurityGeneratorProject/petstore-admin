import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Pet } from './pet.model';
import { PetService } from './pet.service';

@Component({
    selector: 'jhi-pet-detail',
    templateUrl: './pet-detail.component.html'
})
export class PetDetailComponent implements OnInit, OnDestroy {

    pet: Pet;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private petService: PetService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInPets();
    }

    load(id) {
        this.petService.find(id)
            .subscribe((petResponse: HttpResponse<Pet>) => {
                this.pet = petResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInPets() {
        this.eventSubscriber = this.eventManager.subscribe(
            'petListModification',
            (response) => this.load(this.pet.id)
        );
    }
}
