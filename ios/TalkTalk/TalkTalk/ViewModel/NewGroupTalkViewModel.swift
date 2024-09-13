//
//  NewGroupTalkViewModel.swift
//  TalkTalk
//
//  Created by Kacper Grabiec on 13/09/2024.
//

import Foundation

class NewGroupTalkViewModel : ObservableObject {
    @Published var searchQuery: String = ""
    @Published var selectedProfilesUUIDs: Set<UUID> = Set()
}
