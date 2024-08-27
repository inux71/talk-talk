//
//  LoginViewModel.swift
//  TalkTalk
//
//  Created by Kacper Grabiec on 27/08/2024.
//

import Foundation

class LoginViewModel : ObservableObject {
    @Published var email: String = ""
    @Published var password: String = ""
}
