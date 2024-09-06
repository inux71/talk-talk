//
//  TalkListViewModel.swift
//  TalkTalk
//
//  Created by Kacper Grabiec on 27/08/2024.
//

import Foundation

class TalkListViewModel : ObservableObject {
    @Published var searchQuery: String = ""
    @Published var selectedTalkType: TalkType = TalkType.all
}
