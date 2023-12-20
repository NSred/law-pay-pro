import {Component, inject} from '@angular/core';
import {MerchantService} from "../../login-menu/services/merchant.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {NavbarStateService} from "../../navbar-menu/state/navbar-state.service";
import {RegistrationDto} from "../../login-menu/model";
import {ToastrService} from "ngx-toastr";
import {AsyncPipe, NgIf, NgOptimizedImage} from "@angular/common";
import {InputFieldComponent} from "../../components/inputs/input-field/input-field.component";
import {ButtonComponent} from "../../components/buttons/button/button.component";
import {Observable, of} from "rxjs";
import {LabeledInputComponent} from "../../components/labeled-input/labeled-input.component";

export interface RegistrationForm {
  merchantId: FormControl<string>;
  merchantPassword: FormControl<string>;
}

@Component({
  selector: 'app-registration',
  standalone: true,
  imports: [
    NgOptimizedImage,
    InputFieldComponent,
    ButtonComponent,
    NgIf,
    AsyncPipe,
    LabeledInputComponent
  ],
  templateUrl: './registration.component.html',
  styleUrl: './registration.component.scss'
})
export class RegistrationComponent {
  private readonly toastr = inject(ToastrService)
  private readonly merchantService = inject(MerchantService)
  private readonly router = inject(Router)
  private readonly navbarState = inject(NavbarStateService)
  registrationForm = new FormGroup<RegistrationForm>(<RegistrationForm>{
    merchantId: new FormControl('', [Validators.required]),
    merchantPassword: new FormControl('', [Validators.required]),
  })
  apiKey$: Observable<string> = new Observable<string>();
  hideRegisterButton: boolean = false;

  constructor() {
    this.navbarState.updateNavbarVisibility(false);
  }

  register() {
    let request: RegistrationDto = {
      merchantId: this.registrationForm.controls.merchantId.value,
      merchantPassword: this.registrationForm.controls.merchantPassword.value
    }
    this.merchantService.register(request).subscribe({
      next: res => {
        this.apiKey$ = of(res);
        this.navbarState.updateNavbarVisibility(true);
        this.toastr.success('Successfully registered ', 'Success')
        //this.router.navigate(['login']).then()
        this.hideRegisterButton = true;
      },
      error: err => {
        console.log(err)
        //this.toastr.error('Error occurred', 'Error');
      }
    })
  }

  goToLogin() {
    this.router.navigate(['login']).then()
  }
}
