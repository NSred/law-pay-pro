import {Component, inject} from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import {ButtonComponent} from "../../components/buttons/button/button.component";
import {InputFieldComponent} from "../../components/inputs/input-field/input-field.component";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {NavbarStateService} from "../../navbar-menu/state/navbar-state.service";

export interface RegistrationForm {
  name: FormControl<string>;
  surname: FormControl<string>;
  username: FormControl<string>;
  email: FormControl<string>;
  password: FormControl<string>;
}

@Component({
  selector: 'app-registration',
  standalone: true,
    imports: [CommonModule, ButtonComponent, InputFieldComponent, NgOptimizedImage],
  templateUrl: './registration.component.html',
  styleUrl: './registration.component.scss'
})
export class RegistrationComponent {
  private readonly router = inject(Router)
  private readonly navbarState = inject(NavbarStateService)
  registrationForm = new FormGroup<RegistrationForm>(<RegistrationForm>{
    name: new FormControl('', [Validators.required]),
    surname: new FormControl('', [Validators.required]),
    username: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required]),
  })

  constructor() {
    this.navbarState.updateNavbarVisibility(false);
  }

  register() {

  }
}
