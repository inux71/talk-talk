//
//  RegisterViewModel.swift
//  TalkTalk
//
//  Created by Kacper Grabiec on 27/08/2024.
//

import Foundation

class RegisterViewModel : ObservableObject {
    @Published var firstname: String = ""
    @Published var lastname: String = ""
    @Published var email: String = ""
    @Published var password: String = ""
}
