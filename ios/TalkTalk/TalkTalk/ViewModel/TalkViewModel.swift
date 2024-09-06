//
//  TalkViewModel.swift
//  TalkTalk
//
//  Created by Kacper Grabiec on 05/09/2024.
//

import Foundation

class TalkViewModel : ObservableObject {
    @Published var talkName: String = "Kacper Grabiec"
    @Published var message: String = ""
}
