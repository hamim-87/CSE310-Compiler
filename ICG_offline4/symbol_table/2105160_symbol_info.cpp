#include "2105160_symbol_info.hpp"




SymbolInfo::SymbolInfo(const string& _symbolName, const string& _symbolType, SymbolInfo* _nextSymbolInfo, FunctionInfo* _functionInfo,bool _global ) noexcept
{
    this->symbolName = _symbolName;
    this->symbolType = _symbolType;
    this->nextSymbolInfo = _nextSymbolInfo;
    this->functionInfo = _functionInfo;
    this->is_global = _global;
    
}

SymbolInfo::~SymbolInfo() noexcept
{

}

[[nodiscard]] string SymbolInfo::getSymbolName() const noexcept
{
    return this->symbolName;
}

[[nodiscard]] string SymbolInfo::getSymbolType() const noexcept
{
    return this->symbolType;
}

[[nodiscard]] SymbolInfo* SymbolInfo::getNextSymbolInfo() const noexcept
{
    return this->nextSymbolInfo;
}


void SymbolInfo::setSymbolName(const string& _symbolName) noexcept
{
    this->symbolName = _symbolName;
}

void SymbolInfo::setSymbolType(const string& _symbolType) noexcept
{
    this->symbolType = _symbolType;
} 

void SymbolInfo::setNextSymbolInfo(SymbolInfo *_nextSymbolInfo) noexcept
{
    this->nextSymbolInfo = _nextSymbolInfo;
}


