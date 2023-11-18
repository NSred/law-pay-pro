import {AfterViewInit, Component, inject, OnInit} from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import {InputFieldComponent} from "../../components/inputs/input-field/input-field.component";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ButtonComponent} from "../../components/buttons/button/button.component";
import {UserService} from "../services/user.service";
import {LoginDto} from "../../model/login";
import {Router} from "@angular/router";
import {NavbarStateService} from "../../navbar-menu/state/navbar-state.service";
import {HttpClientModule} from "@angular/common/http";

export interface LoginForm {
  username: FormControl<string>;
  password: FormControl<string>;
}

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, NgOptimizedImage, InputFieldComponent, ButtonComponent],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent implements OnInit{
  private readonly userService = inject(UserService)
  private readonly router = inject(Router)
  private readonly navbarState = inject(NavbarStateService)
  loginForm = new FormGroup<LoginForm>(<LoginForm>{
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
  })

  constructor() {
    this.navbarState.updateNavbarVisibility(false);
  }
  ngOnInit(): void {
  }

  login() {
    let request: LoginDto = {
      username: this.loginForm.controls.username.value,
      password: this.loginForm.controls.password.value
    }
    this.router.navigate(['']).then()
    this.navbarState.updateNavbarVisibility(true);
    // this.userService.login(request).subscribe({
    //   next: res => {
    //     this.router.navigate(['']).then()
    //   }
    // })
  }

  goToRegistrationPage() {
    this.router.navigate(['registration']).then()
  }
}
