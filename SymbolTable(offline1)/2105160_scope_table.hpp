#pragma once

#include <string>
#include "2105160_symbol_info.hpp"

using namespace std;

enum HashType{SDBM , DJB2 , FNV1A, MURMUR};

class ScopeTable{
    private:
        SymbolInfo ** buckets;
        int bucketNumber;
        ScopeTable * parentScope;
        int scopeUid;

        int collision;
        HashType type;
    public:
        ScopeTable(const int _bucketNUmber, const int scopeUid,ScopeTable * _parentScope = nullptr,string hashType = "SDBM") noexcept ;
        ~ScopeTable() noexcept;

        [[nodiscard]] int getBucketNumber() const noexcept;
        [[nodiscard]] ScopeTable * getParentScope()  noexcept;
        [[nodiscard]] int getScopeUid() const noexcept;
        [[nodiscard]] int getCollision() noexcept;


        void setBucketNumber(int _bucketNumber) noexcept;
        void setParentScope(ScopeTable* _parentScope) noexcept;
        void setScopeUid(int _scopeUid) noexcept;
        void setHashType(string _type) noexcept;

        bool Insert(string symbolName, string symbolType) noexcept;
        bool Insert(string symbolName, string symbolType, int& totalCollision) noexcept;
        SymbolInfo* LookUp(string symbolName) const noexcept;
        bool Delete(string symbolName) const noexcept;

        void print(int tab = 1) noexcept;

    private:
        unsigned long long getHash(string symbolName, int bucketNumber) const noexcept;
        unsigned long long SDBMHash(string symbolName, int bucketNumber) const noexcept;
        unsigned long long DJB2Hash(string s, int bucketNumber) const noexcept;
        unsigned long long FNV1aHash(string s, int bucketNumber) const noexcept;
        unsigned int MurmurHash3(string key, int bucketNumber) const noexcept;

        

};