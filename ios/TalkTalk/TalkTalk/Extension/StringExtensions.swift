//
//  StringExtensions.swift
//  TalkTalk
//
//  Created by Kacper Grabiec on 06/09/2024.
//

import Foundation

extension String {
    var isBlank: Bool {
        return self.trimmingCharacters(in: .whitespacesAndNewlines).isEmpty
    }
}
