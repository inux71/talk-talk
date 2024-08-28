//
//  UserDefaultsRepository.swift
//  TalkTalk
//
//  Created by Kacper Grabiec on 28/08/2024.
//

import Foundation

class UserDefaultsRepository {
    func getBool(forKey key: String) -> Bool {
        return UserDefaults.standard.bool(forKey: key)
    }
    
    func save<T>(_ value: T, forKey key: String) {
        UserDefaults.standard.set(value, forKey: key)
    }
    
    func clear() {
        let userDefaults: UserDefaults = UserDefaults.standard
        
        userDefaults.dictionaryRepresentation().keys.forEach { key in
            userDefaults.removeObject(forKey: key)
        }
    }
}
