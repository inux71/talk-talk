//
//  TalkType.swift
//  TalkTalk
//
//  Created by Kacper Grabiec on 05/09/2024.
//

import Foundation

enum TalkType : String, CaseIterable {
    case all = "All"
    case unread = "Unread"
    case group = "Groups"
    
    func localizedString() -> String {
        return NSLocalizedString(self.rawValue, comment: "")
    }
}
