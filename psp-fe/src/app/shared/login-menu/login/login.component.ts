import {Component, inject} from '@angular/core';
import {NavbarStateService} from "../../navbar-menu/state/navbar-state.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {LoginDto} from "../model";
import {MerchantService} from "../services/merchant.service";
import {AuthService} from "../auth/auth.service";
import {InputFieldComponent} from "../../components/inputs/input-field/input-field.component";
import {ButtonComponent} from "../../components/buttons/button/button.component";
import {NgOptimizedImage} from "@angular/common";
import {ToastrService} from "ngx-toastr";

export interface LoginForm {
  merchantId: FormControl<string>;
  merchantPassword: FormControl<string>;
}

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    InputFieldComponent,
    ButtonComponent,
    NgOptimizedImage
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent{
  private readonly toastr = inject(ToastrService)
  private readonly auth = inject(AuthService)
  private readonly merchantService = inject(MerchantService)
  private readonly router = inject(Router)
  private readonly navbarState = inject(NavbarStateService)
  loginForm = new FormGroup<LoginForm>(<LoginForm>{
    merchantId: new FormControl('', [Validators.required]),
    merchantPassword: new FormControl('', [Validators.required]),
  })

  constructor() {
    this.navbarState.updateNavbarVisibility(false);
  }

  login(){
    let request: LoginDto = {
      merchantId: this.loginForm.controls.merchantId.value,
      password: this.loginForm.controls.merchantPassword.value
    }
    this.merchantService.login(request).subscribe({
      next: res => {
        this.auth.setToken(res.accessToken)
        this.navbarState.updateNavbarVisibility(true);
        this.toastr.success('Successfully logged in', 'Success')
        this.router.navigate(['']).then()
      },
      error: _ => {
        this.toastr.error('Wrong credentials', 'Error');
      }
    })
  }

  goToRegistrationPage() {
    this.router.navigate(['registration']).then()
  }
}
