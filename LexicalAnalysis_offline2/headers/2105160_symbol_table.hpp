#pragma once

#include <string>
#include "2105160_scope_table.hpp"
#include <iostream>

class SymbolTable{
    private:
        ScopeTable * currentScope;
        int bucketNumberInScope;

        string hash;

        int collison;
        ostream& out;
        

    public:
        int nextScopeId;
        SymbolTable(int bucketNumberInScope = 7, std::string hash = "SDBM", ostream& outputfile = std::cout) noexcept;
        ~SymbolTable() noexcept;

        void EnterScope() noexcept;
        bool ExitScope(bool flag = false) noexcept;
        bool Insert(string symbolName,string symbolType) const noexcept;
        bool Remove(string symbolName) const noexcept;
        SymbolInfo* LookUp(string symbolName) const noexcept;
        void PrintCurrentScopeTable() ;
        void PrintAllScopeTable() const;

        int getAvgCollision() noexcept;


};