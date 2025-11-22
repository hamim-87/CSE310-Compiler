#pragma once

#include <string>
#include "2105160_symbol_info.hpp"
#include <iostream>
using namespace std;

enum HashType{SDBM , DJB2 , FNV1A, MURMUR};



class ScopeTable{
    private:
        SymbolInfo ** buckets;
        int bucketNumber;
        ScopeTable * parentScope;
        //int scopeUid;

        string scopeUid; // (previously interger ,now string)
        int child_scope_count; //new 



        int collision;
        HashType type;
    public:
        ScopeTable(const int _bucketNUmber, const string scopeUid,ScopeTable * _parentScope = nullptr,string hashType = "SDBM") noexcept ;
        ~ScopeTable() noexcept;

        [[nodiscard]] int getBucketNumber() const noexcept;
        [[nodiscard]] ScopeTable * getParentScope()  noexcept;
        // [[nodiscard]] int getScopeUid() const noexcept;
        [[nodiscard]] string getScopeUid() const noexcept;

        [[nodiscard]] int getCollision() noexcept;
        int getChildScopeCount();

        void setBucketNumber(int _bucketNumber) noexcept;
        void setParentScope(ScopeTable* _parentScope) noexcept;
        void setScopeUid(int _scopeUid) noexcept;
        void setHashType(string _type) noexcept;
        void setChildScopeCount(int ct);//

        bool Insert(string symbolName, string symbolType, ostream& out = cout, FunctionInfo* _fuctionInfo = nullptr) noexcept;
        // bool Insert(string symbolName, string symbolType, int& totalCollision) noexcept;
        SymbolInfo* LookUp(string symbolName) const noexcept;
        bool Delete(string symbolName) const noexcept;

        void print(int tab = 1) noexcept;
        void print(ostream &out,bool &can);

    private:
        unsigned long long getHash(string symbolName, int bucketNumber) const noexcept;
        // unsigned long long getHash(const char *p, int bucketNumber) const noexcept;
        unsigned int SDBMHash(string symbolName,unsigned int bucketNumber) const noexcept;
        unsigned long long DJB2Hash(string s, int bucketNumber) const noexcept;
        unsigned long long FNV1aHash(string s, int bucketNumber) const noexcept;
        unsigned int MurmurHash3(string key, int bucketNumber) const noexcept;

        

};