//
//  Profile.swift
//  TalkTalk
//
//  Created by Kacper Grabiec on 16/09/2024.
//

import Foundation

struct Profile : Identifiable, Hashable {
    let id: Int
    let name: String
    let imageURL: URL
}
